import React, { useEffect, useState } from 'react';
import { BrowserRouter as Router, Routes, Route, useLocation, Navigate } from 'react-router-dom';
import { AnimatePresence, motion } from 'framer-motion';
import Navbar from './components/Navbar';
import Footer from './components/Footer';
import Home from './pages/Home';
import Projects from './pages/Projects';
import ProjectDetails from './pages/ProjectDetails';
import Login from './pages/Login';
import Register from './pages/Register';
import Pricing from './pages/Pricing';
import About from './pages/About';

const pageTransition = {
  in: { opacity: 1, x: 0, transition: { duration: 0.5 } },
  out: { opacity: 0, x: '-100%', transition: { duration: 0 } },
};

function AppContent() {
  const location = useLocation();
  const userLogin = localStorage.getItem('login')
  const [isAuthenticated, setIsAuthenticated] = useState(false)

  useEffect(() => {
    if (userLogin) {
      setIsAuthenticated(true)
    } else {
      setIsAuthenticated(false)
    }
  }, [userLogin])

  return (
    <div className="min-h-screen bg-gray-50 flex flex-col">
      {isAuthenticated ? (
        <>
          <Navbar />
          <main className="flex-grow">
            <AnimatePresence exitBeforeEnter>
              <Routes location={location} key={location.pathname}>
                <Route
                  path="/"
                  element={
                    <motion.div initial="out" animate="in" exit="out" variants={pageTransition}>
                      <Home />
                    </motion.div>
                  }
                />
                <Route
                  path="/projects"
                  element={
                    <motion.div initial="out" animate="in" exit="out" variants={pageTransition}>
                      <Projects />
                    </motion.div>
                  }
                />
                <Route
                  path="/projects/:id"
                  element={
                    <motion.div initial="out" animate="in" exit="out" variants={pageTransition}>
                      <ProjectDetails />
                    </motion.div>
                  }
                />
                <Route
                  path="/pricing"
                  element={
                    <motion.div initial="out" animate="in" exit="out" variants={pageTransition}>
                      <Pricing />
                    </motion.div>
                  }
                />
                <Route
                  path="/about"
                  element={
                    <motion.div initial="out" animate="in" exit="out" variants={pageTransition}>
                      <About />
                    </motion.div>
                  }
                />
              </Routes>
            </AnimatePresence>
          </main>
          <Footer />
        </>
      ) : (
        <>
          <Routes location={location} key={location.pathname}>
            <Route
              path="/"
              element={
                <motion.div initial="out" animate="in" exit="out" variants={pageTransition}>
                  <Login />
                </motion.div>
              }
            />
            <Route
              path="/register"
              element={
                <motion.div initial="out" animate="in" exit="out" variants={pageTransition}>
                  <Register />
                </motion.div>
              }
            />
            <Route path="*" element={<Navigate to="/" />} />
          </Routes>
        </>
      )}
    </div>
  );
}

// Внешний компонент, который содержит Router
function App() {
  return (
    <Router>
      <AppContent />
    </Router>
  );
}

export default App;