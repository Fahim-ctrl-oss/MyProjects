// db/models/User.js
import BaseModel from './BaseModel.js';

export default class User extends BaseModel {
  constructor(db) {
    super(db, 'Users');
  }

  async create(name, role) {
    const result = await this.db.run(
      `INSERT INTO Users (name, role) VALUES (?, ?)`,
      name,
      role
    );
    return result.lastID; 
  }

  async update(id, fields) {
    const updates = Object.keys(fields)
      .map((key) => `${key} = ?`)
      .join(', ');
    const values = Object.values(fields);
    values.push(id);
    await this.db.run(
      `UPDATE ${this.tableName} SET ${updates} WHERE id = ?`,
      ...values
    );
  }
}
