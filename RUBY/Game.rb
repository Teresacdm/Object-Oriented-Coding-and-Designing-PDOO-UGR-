#encoding: UTF-8
module Irrgarten

require_relative 'labyrinth.rb'
require_relative 'gameState.rb'
require_relative 'player.rb'
require_relative 'dice'
require_relative 'Orientation'
require_relative 'monster'
require_relative 'directions'
require_relative 'game_character'

class Game
	@@MAX_ROUNDS = 10

	def initialize(nplayers)
		@players = []
		for i in (0...nplayers)
			p = Player.new(i, Dice.randomIntelligence, Dice.randomStrength)
			@players<<p
		end
		@monsters=[]
		@labyrinth = Irrgarten::Labyrinth.new(6, 4, 3, 3)
		@currentPlayerIndex = Irrgarten::Dice.whoStarts(@players.size)
		configureLabyrinth
		@labyrinth.spreadPlayers(@players)
		@log = "Comienza la partida!\n"
		@currentPlayer = @players[@currentPlayerIndex]

	end

	def finished
		@labyrinth.haveAWinner #no se si hay que poner ()
	end


	def nextStep( preferredDirection)
		dead = @currentPlayer.dead

		if !dead
			direction = actualDirection(preferredDirection)
			if direction != preferredDirection
				logPlayerNoOrders
			end
			monster = @labyrinth.putPlayer(direction, @currentPlayer)
			if monster == nil
				logNoMonster
			else			## si quisieramos poner mas if seria: elsif
				winner = combat(monster)
				manageReward(winner)
			end
		else
			manageResurrection
		end
		endGame = finished
		if !endGame
			nextPlayer
		end
		endGame
	end

	def GameState
		GameState.new(@labyrinth.to_s, @players.to_s, @monsters.to_s, @currentPlayerIndex, finished, @log)
	end

	private
	def configureLabyrinth
		v = Orientation::VERTICAL
		h = Orientation::HORIZONTAL
		@labyrinth.addBlock(v, 0, 1, 1)
		@labyrinth.addBlock(v, 0, 3, 1)
		@labyrinth.addBlock(v, 2, 3, 1)
		@labyrinth.addBlock(v, 2, 0, 2)
		@labyrinth.addBlock(h, 4, 2, 2)
		@labyrinth.addBlock(v, 5, 0, 1)
		monster1 = Irrgarten::Monster.new('Marisol', Dice.randomIntelligence, Dice.randomStrength)
		@labyrinth.addMonster(3, 1, monster1)
		@monsters<<monster1
		monster2 = Monster.new('TerraLuna', Dice.randomIntelligence, Dice.randomStrength)
		@labyrinth.addMonster(5, 2, monster2)
		@monsters<<monster2
	end

	def nextPlayer()
		#if @currentPlayerIndex != (@players.size - 1)
		#	@currentPlayerIndex += 1
		#else
		#	@currentPlayerIndex = 0
		#	@currentPlayer = @players[@currentPlayerIndex]
		#end
		total = @players.size
		@currentPlayerIndex = (@currentPlayerIndex + 1)%total
		@currentPlayer = @players[@currentPlayerIndex]

	end

	def actualDirection(preferredDirection)
		currentRow = @currentPlayer.getRow
		currentCol = @currentPlayer.getCol
		output = @currentPlayer.move(preferredDirection, @labyrinth.validMoves(currentRow, currentCol))
	end


	def combat(monster)
		rounds = 0
		winner = GameCharacter::PLAYER
		playerAttack = @currentPlayer.attack
		lose = monster.defend(playerAttack)

		while !lose && (rounds < @@MAX_ROUNDS)
			winner = GameCharacter::MONSTER
			rounds+=1
			monsterAttack = monster.attack
			lose = @currentPlayer.defend(monsterAttack)
			if !lose
				playerAttack = @currentPlayer.attack
				winner = GameCharacter::PLAYER
				lose = monster.defend(playerAttack)
			end
		end
		logRounds(rounds, @@MAX_ROUNDS)
		winner
	end


	def manageReward(winner)
		if winner==GameCharacter::PLAYER
			@currentPlayer.receiveReward
		logPlayerWon
		else
			logMonsterWon
		end
	end



	def manageResurrection()
		resurrect = Dice.resurrectPlayer
		if resurrect
			currentPlayer.resurrect
			logResurrected
		else
			logPlayerSkipTurn
		end
	end


	def logPlayerWon()
		@log+="El jugador #{@currentPlayer.getNumber} ha ganado el combate \n"
	end


	def logMonsterWon()
		@log+="El monstruo ha ganado el combate \n"
	end

	def logResurrected()
		@log+="El jugador #{@currentPlayer.getNumber} ha resucitado \n"
	end

	def logPlayerSkipTurn()
		@log+="El jugador #{@currentPlayer.getNumber} ha perdido el turno por estar muerto \n"
	end


	def logPlayerNoOrders()
		@log+="El jugador #{@currentPlayer.getNumber} no ha ha seguido las instrucciones del jugador humano \n"
	end

	def logNoMonster()
		"El jugador #{@currentPlayer.getNumber} se ha movido a una celda vacía o no le ha sido posible moverse\n "
	end

	def logRounds(rounds, max)
		puts "Se han producido #{rounds} rondas de #{max} en el combate"
	end
end
end
