import { Socket } from "socket.io-client";
import type { ClientToServerEvents, ServerToClientEvents } from "../hooks/useSocket";

export const joinRoom = (
  socket: Socket<ServerToClientEvents, ClientToServerEvents> | null,
  roomId: number = 5
) => {
  if (!socket || !socket.id) return;

  socket.emit("join room", { 
    player: socket.id, roomId: roomId });
  console.log("Joining room:", { player: socket.id, roomId });
};

export const leaveRoom = (
  socket: Socket<ServerToClientEvents, ClientToServerEvents> | null,
  roomId: number = 5
) => {
  if (!socket || !socket.id) return;

  socket.emit("leave room", { player: socket.id, roomId: roomId });
  console.log("Leaving room:", { player: socket.id, roomId });
};
