#encoding: UTF-8
module Irrgarten

class Dice
  @@MAX_USES = 5
  @@MAX_INTELLIGENCE = 10.0
  @@MAX_STRENGTH = 10.0
  @@RESURRECT_PROB = 0.3
  @@WEAPONS_REWARD = 2
  @@SHIELDS_REWARD = 3
  @@HEALTH_REWARD = 5
  @@MAX_ATTACK = 3
  @@MAX_SHIELD = 2

  @@generator = Random.new

  def self.randomPos(max)
    @@generator.rand(max)
  end

  def self.whoStarts(nplayers)
    @@generator.rand(nplayers)
  end

  def self.randomIntelligence
    (@@MAX_INTELLIGENCE * @@generator.rand).to_f
  end

  def self.randomStrength
    (@@MAX_STRENGTH * @@generator.rand).to_f
  end

  def self.resurrectPlayer
    prob = @@generator.rand
    prob <= @@RESURRECT_PROB
  end

  def self.weaponsReward
    @@generator.rand(@@WEAPONS_REWARD + 1)
  end

  def self.shieldsReward
    @@generator.rand(@@SHIELDS_REWARD + 1)
  end

  def self.healthReward
    @@generator.rand(@@HEALTH_REWARD + 1)
  end

  def self.weaponPower
    (@@MAX_ATTACK * @@generator.rand).to_f
  end

  def self.shieldPower
    (@@MAX_SHIELD * @@generator.rand).to_f
  end

  def self.usesLeft
    @@generator.rand(@@MAX_USES + 1)
  end

  def self.intensity(competence)
    (competence * @@generator.rand).to_f
  end

  def self.discardElement(uses_left)
    if uses_left == 0
      true
    else
      porc = 1.0 / (uses_left.to_f / @@MAX_USES)
      prob = @@generator.rand
      prob >= porc
    end
  end

  def self.nextStep(preference, valid_moves, intelligence)
    prob = @@generator.rand.to_f    #nse si es asi
    prob_int= intelligence/@@MAX_INTELLIGENCE
    if prob_int >= prob
      if valid_moves.contains(preference)
        return preference
      end
    else
      return validMoves.get(@@generator.rand(validMoves.size))
    end
  end
end
end
