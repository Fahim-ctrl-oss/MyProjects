import { joinRoom, leaveRoom, printPlayersInRoom } from "./roomSockets/roomSockets.js";

export default (io) => {
    io.on('connection', (socket) => {
        console.log('User connected:', socket.id);

        joinRoom(io, socket);
        leaveRoom(io, socket);

        socket.on('disconnect', () => {
            console.log('User disconnected:', socket.id);
        });
    });
};
