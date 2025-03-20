import { Code2, Layout, Database } from 'lucide-react';
import {Link} from "react-router-dom";

const Projects = () => {

  const projects = [
    {
      id: 1,
      title: "E-commerce API",
      description: "Build a RESTful API for an e-commerce platform with user authentication and product management.",
      difficulty: "intermediate",
      type: "backend",
      icon: Database,
    },
    {
      id: 2,
      title: "Portfolio Website",
      description: "Create a responsive portfolio website with modern design principles and animations.",
      difficulty: "beginner",
      type: "frontend",
      icon: Layout,
    },
    {
      id: 3,
      title: "Task Management App",
      description: "Develop a full-stack task management application with real-time updates.",
      difficulty: "advanced",
      type: "fullstack",
      icon: Code2,
    },
  ];

  return (
    <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-8">
      <div className="flex flex-col md:flex-row md:items-center md:justify-between mb-8">
        <div>
          <h1 className="text-3xl font-bold text-gray-900">Projects</h1>
          <p className="mt-2 text-gray-500">Find the perfect project to enhance your skills</p>
        </div>
      </div>
      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
        {projects.map((project) => (
          <div
            key={project.id}
            className="bg-white rounded-lg shadow-sm border border-gray-200 hover:shadow-md transition-shadow duration-200"
          >
            <div className="p-6">
              <div className="flex items-center mb-4">
                <div className="h-10 w-10 rounded-full bg-indigo-100 flex items-center justify-center">
                  <project.icon className="h-6 w-6 text-indigo-600" />
                </div>
                <div className="ml-4">
                  <h3 className="text-lg font-medium text-gray-900">{project.title}</h3>
                  <span className={`
                    inline-flex items-center px-2.5 py-0.5 rounded-full text-xs font-medium
                    ${project.difficulty === 'beginner' ? 'bg-green-100 text-green-800' :
                      project.difficulty === 'intermediate' ? 'bg-yellow-100 text-yellow-800' :
                        'bg-red-100 text-red-800'}
                  `}>
                    {project.difficulty}
                  </span>
                </div>
              </div>
              <p className="text-gray-500 text-sm mb-4">{project.description}</p>
              <Link to={`/projects/${project.id}`}>
              <button className="w-full bg-indigo-600 text-white py-2 px-4 rounded-md hover:bg-indigo-700 transition-colors duration-200">
                  Start Project
              </button>
              </Link>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default Projects;