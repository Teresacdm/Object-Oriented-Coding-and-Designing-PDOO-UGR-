#encoding: UTF-8

module Irrgarten

class GameState
	def initialize (_lab, _play, _monst, _currPlay, _win, _log) #constructor
		@labyrinth=_lab
		@players=_play
		@monsters=_monst
		@currentPlayer=_currPlay
		@winner=_win
		@log=_log
	end
	
								#Todos los métodos son públicos por defecto
	def labyrinth
		@labyrinth
	end
	
	def players
		@players
	end
	
	def monsters
		@monsters
	end
	
	def currentPlayer
		@currentPlayer
	end
	
	def winner
		@winner
	end
	
	def log
		@log
	end
end
end

	
	

		
