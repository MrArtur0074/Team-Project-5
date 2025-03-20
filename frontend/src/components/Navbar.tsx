import  { useState } from 'react';
import { Link } from 'react-router-dom';
import { Code2, Menu } from 'lucide-react';

const Navbar = () => {
  const [isOpen, setIsOpen] = useState(false);

  const toggleMenu = () => {
    setIsOpen(!isOpen);
  };

  const name = localStorage.getItem('username')

  return (
    <nav className="bg-white border-b border-gray-100">
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div className="flex justify-between h-16">
          <div className="flex">
            <Link to="/" className="flex items-center">
              <Code2 className="h-8 w-8 text-indigo-600"/>
              <span className="ml-2 text-xl font-bold text-gray-900">SkillForge</span>
            </Link>
            <div className="hidden sm:ml-6 sm:flex sm:space-x-8">
              <Link to="/projects"
                    className="inline-flex items-center px-1 pt-1 text-sm font-medium text-gray-500 hover:text-gray-900">
                Projects
              </Link>
              <Link to="/pricing"
                    className="inline-flex items-center px-1 pt-1 text-sm font-medium text-gray-500 hover:text-gray-900">
                Pricing
              </Link>
              <Link to="/about"
                    className="inline-flex items-center px-1 pt-1 text-sm font-medium text-gray-500 hover:text-gray-900">
                About
              </Link>
            </div>
          </div>
          <div className="flex">
            <div className="hidden sm:ml-6 sm:flex sm:space-x-8">
              <span
                    className="inline-flex items-center px-1 pt-1 text-sm font-medium text-gray-500 hover:text-gray-900">
                {name}
              </span>
            </div>
          </div>
          <div className="flex items-center sm:hidden">
            <button type="button" className="text-gray-500 hover:text-gray-900" onClick={toggleMenu}>
              <Menu className="h-6 w-6"/>
            </button>
          </div>
        </div>
        {isOpen && (
            <div className="sm:hidden">
              <div className="pt-2 pb-3 space-y-1">
                <Link onClick={toggleMenu} to="/projects"
                      className="block px-3 py-2 rounded-md text-base font-medium text-gray-500 hover:text-gray-900">
                  Projects
                </Link>
                <Link onClick={toggleMenu} to="/pricing"
                      className="block px-3 py-2 rounded-md text-base font-medium text-gray-500 hover:text-gray-900">
                  Pricing
                </Link>
                <Link onClick={toggleMenu} to="/about"
                      className="block px-3 py-2 rounded-md text-base font-medium text-gray-500 hover:text-gray-900">
                  About
                </Link>
              </div>
            </div>
        )}
      </div>
    </nav>
  );
};

export default Navbar;