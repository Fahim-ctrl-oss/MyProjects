// src/game/engine.js
import { Doctor, Mafia, Villager } from './roles.js';

export const Phases = {
  WAITING: 'waiting',
  NIGHT: 'night',
  DAY: 'day'
};

export class Engine {
  constructor() {
    this.players = []; 
    this.phase = Phases.WAITING;
    this.savedPlayers = new Set(); 
    this.killedPlayers = new Set(); 
  }

  addPlayer(id) {
    this.players.push({ id, role: null, alive: true });
  }

  removePlayer(id) {
    this.players = this.players.filter(p => p.id !== id);
  }

  assignRoles(roleList) {
    const shuffled = this.shuffle(roleList);
    this.players.forEach((p, i) => {
      p.role = shuffled[i];
    });
  }

  shuffle(array) {
    return array.sort(() => Math.random() - 0.5);
  }

  savePlayer(playerId) {
    this.savedPlayers.add(playerId);
  }

  killPlayer(playerId) {
    const player = this.players.find(p => p.id === playerId);
    if (!player || !player.alive) return;
    if (this.savedPlayers.has(playerId)) return; 
    player.alive = false;
    this.killedPlayers.add(playerId);
  }

  getState() {
    return this.players.map(p => ({
      id: p.id,
      role: p.role ? p.role.name : null,
      alive: p.alive
    }));
  }
  printPlayers() {
    console.log("Players in room:");
    this.players.forEach(p => {
      console.log(
        `ID: ${p.id}, Role: ${p.role ? p.role.name : 'Unassigned'}, Alive: ${p.alive}`
      );
    });
  }
}
