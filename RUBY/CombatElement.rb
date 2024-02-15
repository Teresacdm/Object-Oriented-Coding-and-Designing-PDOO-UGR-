#encoding: UTF-8
module Irrgarten

  class CombatElement
    def inicialize(effect, uses)
      @effect=effect
      @uses=uses
    end

    def produceEffect
      if uses>0
        uses-=1
        return effect    #nse si los return funcionan asi, o hay que crear una variable aux y devolverla
      else
        return 0
      end
    end

    def discard
      Dice.discardElement(uses)
    end

    def to_s
      "[ #effect+","+uses+"]"
    end
  end
end

