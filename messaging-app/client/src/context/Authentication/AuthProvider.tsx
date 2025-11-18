// src/context/Authentication/AuthProvider.tsx
import type { ReactNode } from "react";
import { useState } from "react";
import { AuthContext } from "./AuthContext";
import type { AuthContextType } from "./types";

interface AuthProviderProps {
  children: ReactNode;
}

export const AuthProvider = ({ children }: AuthProviderProps) => {
  const [user, setUser] = useState<string | null>(null);

  const login = (username: string) => setUser(username);
  const logout = () => setUser(null);

  const value: AuthContextType = { user, login, logout };

  return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>;
};
