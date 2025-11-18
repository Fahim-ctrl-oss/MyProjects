// src/store/roomStore.js
const rooms = new Map(); 

export default {
  get: (roomId) => rooms.get(roomId),
  set: (roomId, gameInstance) => rooms.set(roomId, gameInstance),
  delete: (roomId) => rooms.delete(roomId),
  all: () => Array.from(rooms.values())
};
