require_relative 'Game'
require_relative 'UI/textUI'
require_relative 'Control/controller'

textUI = UI::TextUI.new
game = Irrgarten::Game.new(1)
controller = Control::Controller.new(game, textUI)
controller.play
