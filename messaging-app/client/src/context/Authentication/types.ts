// src/context/Authentication/types.ts
export interface AuthContextType {
  user: string | null;
  login: (username: string) => void;
  logout: () => void;
}
