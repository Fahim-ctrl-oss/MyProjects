// src/sockets/roomSockets.js
import RoomStore from '../../store/roomStore.js';

export function joinRoom(io, socket) {
  socket.on('join room', ({ player, roomId }) => {
    socket.join(roomId);

    // Create room if it doesn't exist
    let room = RoomStore.get(roomId);
    if (!room) {
      room = { id: roomId, players: [] };
      RoomStore.set(roomId, room);
    }

    // Add player if not present
    if (!room.players.find(p => p.id === player)) {
      room.players.push({ id: player, role: null, alive: true });
      console.log(`${player} joined room ${roomId}`);
    } else {
      console.log(`${player} already in room ${roomId}`);
    }
    

    io.to(roomId).emit('update', `Player ${player} joined!`);
    //printPlayersInRoom(roomId);
  });
}

export function leaveRoom(io, socket) {
  socket.removeAllListeners('leave room');
  socket.on('leave room', ({ player, roomId }) => {
    socket.leave(roomId);

    const room = RoomStore.get(roomId);
    
    if (!room) {
      console.log(`${player} not in room ${roomId}`);
      return;
    }

    const playerExists = room.players.some(p => p.id === player);
    if (!playerExists) {
      console.log(`${player} not in room ${roomId}`);
      return;
    }

    room.players = room.players.filter(p => p.id !== player);
    io.to(roomId).emit('update', room.players);
    console.log(`${player} left room ${roomId}`);

    if (room.players.length === 0) RoomStore.delete(roomId);
    
  });
}

export function printPlayersInRoom(roomId) {
  const room = RoomStore.get(roomId);
  if (!room) {
    console.log(`Room ${roomId} does not exist.`);
    return;
  }

  if (!room.players.length) {
    console.log(`Room ${roomId} has no players.`);
    return;
  }

  console.log(`Players in room ${roomId}:`);
  room.players.forEach(p => {
    console.log(`\tID: ${p.id}, Role: ${p.role || 'Unassigned'}, Alive: ${p.alive}`);
  });
}
