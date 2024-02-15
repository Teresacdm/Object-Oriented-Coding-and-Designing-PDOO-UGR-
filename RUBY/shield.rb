# frozen_string_literal: true
module Irrgarten
class Shield
  def initialize(protection, uses)
    @protection=protection
    @uses=uses
  end

  def protect
    if @uses>0
      uses-=1
      @protection
    else
      0
    end
  end

  def to_s
    "S[ #{@protection}, #{@uses}]"
  end

  def discard
    Dice.discardElement(@uses)
  end
end
end
