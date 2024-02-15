# frozen_string_literal: true
require_relative 'player'
require_relative 'monster'
require_relative 'dice'
require_relative 'directions'
require_relative 'Orientation'

module Irrgarten
class Labyrinth
  @@BLOCK_CHAR = 'X'
  @@EMPTY_CHAR = '-'
  @@MONSTER_CHAR = 'M'
  @@COMBAT_CHAR = 'C'
  @@EXIT_CHAR = 'E'
  @@ROW = 0
  @@COL = 1

  def initialize(nRows, nCols, exitRow, exitCol)
    @nRows=nRows
    @nCols=nCols
    @exitRow=exitRow
    @exitCol=exitCol
    #@monsters = Array.new(@nRows, @nCols)
    @monsters = Array.new(@nRows) { Array.new(@nCols) {nil} }
    @players = Array.new(@nRows) { Array.new(@nCols) {nil} }
    @labyrinth = Array.new(@nRows) { Array.new(@nCols) {@@EMPTY_CHAR} }
    #for i in (0..@nRows)
    # for j in (0..@nCols)
    #   @labyrinth[i][j]=@@EMPTY_CHAR
    # end
    #end
    @labyrinth[@exitRow][@exitCol]=@@EXIT_CHAR
  end

  def spreadPlayers(players)
    pos = [0, 0]
    for i in (0...players.size)
      p = players[i]
      pos = randomEmptyPos
      putPlayer2D(-1, -1, pos[@@ROW], pos[@@COL], p)
    end
  end

  def haveAWinner
    @players[@exitRow][@exitCol]!=nil
  end

  def to_s
    matrix = "\n"
    for i in (0...@nRows)
      matrix += "| "
      for j in (0...@nCols)
        matrix += "#{@labyrinth[i][j]} | "
      end
      matrix +="\n"
    end
    matrix
  end

  def addMonster(row, col, monster)
    if posOK(row,col) && emptyPos(row,col)
      @labyrinth[row][col]=@@MONSTER_CHAR
      monster.setPos(row, col)
      @monsters[row][col]=monster
    end
  end

  def putPlayer(direction, player)
    oldRow=player.getRow
    oldCol=player.getCol
    newPos = dir2Pos(oldRow, oldCol, direction)
    putPlayer2D(oldRow, oldCol, newPos[@@ROW], newPos[@@COL], player)
  end

  def addBlock(orientation, startRow, startCol, length)
    if orientation == Orientation::VERTICAL
      incRow=1
      incCol=0
    else
      incRow=0
      incCol=1
    end
    row=startRow
    col=startCol
    while posOK(row,col) && emptyPos(row,col) && length>0
      @labyrinth[row][col]=@@BLOCK_CHAR
      length-=1
      row+=incRow
      col+=incCol
    end
  end

  def validMoves(row,col)
    output = Array.new()
    if canStepOn(row+1,col)
      output << Directions::DOWN
    end
    if canStepOn(row-1,col)
      output << Directions::UP
    end
    if canStepOn(row,col+1)
      output << Directions::RIGHT
    end
    if canStepOn(row,col-1)
      output << Directions::LEFT
    end
    output
  end

  private
  def posOK(row,col)
    row>=0 && row<@nRows && col>=0 && col<@nCols
  end

  def emptyPos(row,col)
    @labyrinth[row][col]==@@EMPTY_CHAR
  end

  def monsterPos(row,col)
    @labyrinth[row][col]==@@MONSTER_CHAR
  end

  def exitPos(row,col)
    @labyrinth[row][col]==@@EXIT_CHAR
  end

  def combatPos(row,col)
    @labyrinth[row][col]==@@COMBAT_CHAR
  end

  def canStepOn(row,col)
    if posOK(row,col)
      if emptyPos(row,col) || monsterPos(row,col) || exitPos(row,col)
        true
      else
        false
      end
    else
      false
    end
  end

  def updateOldPos(row,col)
    if posOK(row,col)
      if combatPos(row,col)
        @labyrinth[row][col]=@@MONSTER_CHAR
      else
        @labyrinth[row][col]=@@EMPTY_CHAR
      end
    end
  end

  def dir2Pos(row,col,direction)
    newcol=col
    newrow=row
    newpos=Array.new(2)
    case direction
    when Directions::LEFT
      if canStepOn(newrow, col-1)
        newcol-=1
      end
    when Directions::RIGHT
      if canStepOn(newrow, col+1)
        newcol+=1
      end
    when Directions::UP
      if canStepOn(row-1, newcol)
        newrow-=1
      end
    when Directions::DOWN
      if canStepOn(row+1, newcol)
        newrow+=1
      end
    end
    newpos[0]=newrow
    newpos[1]=newcol
    newpos
  end

  def randomEmptyPos
    newpos=Array.new(2)
    begin
      newrow=Dice.randomPos(@nRows)
      newcol=Dice.randomPos(@nCols)
    end while (!emptyPos(newrow, newcol))
    newpos[@@ROW]=newrow
    newpos[@@COL]=newcol
    newpos
  end

  def putPlayer2D(oldRow, oldCol, row, col, player)
    output=nil
    if canStepOn(row,col)
      if posOK(oldRow,oldCol)
        p = @players[oldRow][oldCol]
        if p == player
          updateOldPos(oldRow,oldCol)
          @players[oldRow][oldCol]=nil
        end
      end
      if monsterPos(row, col)
        @labyrinth[row][col]=@@COMBAT_CHAR
        output=@monsters[row][col]
      else
        @labyrinth[row][col]=player.getNumber
      end
      @players[row][col]=player
      player.setPos(row,col)
    end
    output
  end
end
end

