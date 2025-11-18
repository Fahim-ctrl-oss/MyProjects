// models/BaseModel.js
export default class BaseModel {
  constructor(db, tableName) {
    this.db = db;
    this.tableName = tableName;
  }

  async findAll() {
    return this.db.all(`SELECT * FROM ${this.tableName}`);
  }

  async findById(id) {
    return this.db.get(`SELECT * FROM ${this.tableName} WHERE id = ?`, id);
  }

  async deleteAll() {
    await this.db.exec(`DELETE FROM ${this.tableName}`);
    const seqTable = await this.db.get(
      "SELECT name FROM sqlite_master WHERE type='table' AND name='sqlite_sequence';"
    );
    if (seqTable) {
      await this.db.exec(`DELETE FROM sqlite_sequence WHERE name="${this.tableName}"`);
    }
  }
}
