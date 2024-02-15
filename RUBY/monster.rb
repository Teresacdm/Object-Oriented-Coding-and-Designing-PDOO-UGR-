# frozen_string_literal: true
module Irrgarten

require_relative 'dice'

class Monster
  @@INITIAL_HEALTH =5

  def initialize(name, intelligence, strength)
    @name=name
    @intelligence=intelligence
    @strength=strength
    @health=@@INITIAL_HEALTH
    #@row #Inicializamos row y col aunque no se pongan en el constructor en java?
    #@col
  end

  def dead
    @health<=0
  end

  def attack
    Dice.intensity(@strength)
  end

  def defend(receivedAttack)
    isDead = dead
    if !isDead
      defensiveEnergy = Dice.intensity(@intelligence)
      if defensiveEnergy<receivedAttack
         gotWounded
         isDead = dead
      end
    end
    isDead
  end

  def setPos(row, col)
    @row=row
    @col=col
  end

  def to_s
    "Nombre del Monstruo: #{@name}, Fuerza: #{@strength}, Inteligencia: #{@intelligence},
      Salud: #{@health}, Fila: #{@row}, Columna: #{@col}"
  end

  def gotWounded
    @health-=1
  end

  private :gotWounded

end
end
