# frozen_string_literal: true

require_relative 'weapon'
require_relative 'shield'
require_relative 'dice'
require_relative 'directions'


module Irrgarten
class Player
  @@MAX_WEAPONS = 2
  @@MAX_SHIELDS = 3
  @@INITIAL_HEALTH = 10
  @@HITS2LOSE = 3

  attr_reader :number
  def initialize (num,  inteligencia, fuerza)
    @number = num
    @name = "Player ##{@number}"
    @intelligence = inteligencia
    @strength = fuerza
    @health = @@INITIAL_HEALTH
    @consecutiveHits = 0
    @weapons = []
    @shields = []
  end

  def getRow
    @row
  end

  def getCol
    @col
  end

  def getNumber
   @number
  end

  def setPos(row, col)
    @row = row
    @col = col
  end

  def resurrect
    @health=@@INITIAL_HEALTH
    resetHits
    @weapons.clear
    @shields.clear
  end

  def dead
    @health<=0
  end

  def move(direction, validMoves)
    if validMoves.size > 0 && !validMoves.include?(direction)
      firstElement=validMoves[0]
    else
      direction
    end
  end

  def attack
    @strength + sumWeapons
  end

  def defend (receivedAttack)
    manageHit(receivedAttack)
  end

  def receiveReward
    wReward = Dice.weaponsReward
    sReward = Dice.shieldsReward
    for i in (0...wReward)
      wnew = Weapon.new(Dice.weaponPower, Dice.usesLeft)
      receiveWeapon(wnew)
    end
    for i in (0...sReward)
      snew = Shield.new(Dice.shieldPower, Dice.usesLeft)
      receiveShield(snew)
    end
    @health += Dice.healthReward
  end

  def to_s
    "Nombre del Jugador: #{@name}, \nNumero: #{@number}, \nFuerza: #{@strength}, \nInteligencia: #{@intelligence},
    \nSalud: #{@health}, \nFila: #{@row}, \nColumna: #{@col}, \nGolpes Consecutivos: #{@consecutiveHits}, \nArmas: #{@weapons},
    \nEscudos: #{@shields}"
  end

  private
  def receiveWeapon(w)
    for i in (0...@weapons.size)
      if @weapons[i].discard
        @weapons.delete_at(i)
      end
    end
    if @weapons.size < @@MAX_WEAPONS
      @weapons << w
    end
  end

  def receiveShield(s)
    for i in (0...@shields.size)
      if @shields[i].discard
        @shields.delete_at(i)
      end
    end
    if @shields.size < @@MAX_SHIELDS
      @shields << s
    end
  end


  def newWeapon
    Weapon.new(Dice.weaponPower, Dice.usesLeft)
  end

  def newShield
    Shield.new(Dice.shieldPower, Dice.usesLeft)
  end

  def sumWeapons
    total=0
    for i in (0...@weapons.size)
      total += @weapons[i].attack
    end
    total
  end

  def sumShields
    total=0
    for i in (0...@shields.size)
      total+=@shields[i].protect
    end
    total
  end

  def defensiveEnergy
    @intelligence + sumShields
  end

  def manageHit(receivedAttack)
    if defensiveEnergy < receivedAttack
      gotWounded
      incConsecutiveHits
    else
      resetHits
    end
    if @consecutiveHits == @@HITS2LOSE || dead
      resetHits
      lose=true
    else
      lose=false
    end
  end

  def resetHits
    @consecutiveHits=0
  end

  def gotWounded
    @health-=1
  end

  def incConsecutiveHits
    @consecutiveHits+=1
  end

end
end