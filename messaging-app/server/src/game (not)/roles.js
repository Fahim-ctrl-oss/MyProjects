class Role {
  constructor(name, type) {
    this.name = name;
    this.type = type;
  }
  onNightAction(game, playerId, targetId) {}
}

export class Doctor extends Role {
  constructor() { super('Doctor', 'town'); }
  onNightAction(game, playerId, targetId) { game.savePlayer(targetId); }
}

export class Mafia extends Role {
  constructor() { super('Mafioso', 'mafia'); }
  onNightAction(game, playerId, targetId) { game.killPlayer(targetId); }
}

export class Villager extends Role {
  constructor() { super('Villager', 'villager'); }
  onNightAction(game, playerId, targetId) {  }
}
