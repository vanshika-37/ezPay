import { createContext, useState } from "react";

/**
    @author: Vanshika Sood
    @since: 6th September 2024 
    
    UserContext is a React Context object that provides the userId and setUserId functions to all components in the component tree.
    
**/

// Create the UserContext with default values
export const UserContext = createContext();

export const UserProvider = ({ children }) => {
  const [userId, setUserId] = useState(5678); // Default userId

  return (
    <UserContext.Provider value={{ userId, setUserId }}>
      {children}
    </UserContext.Provider>
  );
};
