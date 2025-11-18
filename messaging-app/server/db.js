import sqlite3 from 'sqlite3';
import { open } from 'sqlite';
import path from 'path';
import fs from 'fs';

const __dirname = path.resolve();

// Ensure the data folder exists
const dataDir = path.join(__dirname, 'data');
if (!fs.existsSync(dataDir)) {
  fs.mkdirSync(dataDir, { recursive: true });
}

export async function connectDB() {
  const db = await open({
    filename: path.join(dataDir, 'database.sqlite'),
    driver: sqlite3.Database,
  });

  // Create the table if it doesn't exist
  await db.run(`
    CREATE TABLE IF NOT EXISTS players (
      id INTEGER PRIMARY KEY AUTOINCREMENT,
      name TEXT,
      role TEXT
    )
  `);

  return db;
}

export async function clearDB(db) {
  
  await db.exec('PRAGMA foreign_keys = OFF;');

  await db.exec('DELETE FROM Users;');

  const seqTable = await db.get(
    "SELECT name FROM sqlite_master WHERE type='table' AND name='sqlite_sequence';"
  );
  if (seqTable) {
    await db.exec('DELETE FROM sqlite_sequence WHERE name="players";');
  }

  await db.exec('PRAGMA foreign_keys = ON;');

}

export async function initDB(db) {
  await db.run(`
    CREATE TABLE IF NOT EXISTS Users (
      id INTEGER PRIMARY KEY AUTOINCREMENT,
      name TEXT NOT NULL,
      role TEXT NOT NULL
    )
  `);
}

export async function disconnectDB(db) {
  if (db) { await db.close(); }
}
