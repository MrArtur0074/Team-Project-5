import React from 'react';
import { useParams } from 'react-router-dom';
import { Clock, Users, BarChart, MessageSquare, Github } from 'lucide-react';

const ProjectDetails = () => {
  const { id } = useParams();

  // Mock project data
  const project = {
    id: 1,
    title: "E-commerce API",
    description: "Build a RESTful API for an e-commerce platform with user authentication and product management. This project will help you learn modern backend development practices, database design, and security principles.",
    difficulty: "intermediate",
    type: "backend",
    duration: "4 weeks",
    participants: 12,
    technologies: ["Node.js", "Express", "PostgreSQL", "JWT"],
    requirements: [
      "Basic understanding of JavaScript",
      "Familiarity with REST APIs",
      "Knowledge of SQL databases"
    ],
    learningOutcomes: [
      "Design and implement RESTful APIs",
      "Handle user authentication and authorization",
      "Work with relational databases",
      "Implement security best practices"
    ]
  };

  return (
    <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-8">
      <div className="bg-white shadow-sm rounded-lg overflow-hidden">
        {/* Project Header */}
        <div className="px-6 py-8 border-b border-gray-200">
          <div className="flex items-center justify-between">
            <h1 className="text-3xl font-bold text-gray-900">{project.title}</h1>
            <span className="inline-flex items-center px-3 py-1 rounded-full text-sm font-medium bg-yellow-100 text-yellow-800">
              {project.difficulty}
            </span>
          </div>
          <p className="mt-4 text-gray-600">{project.description}</p>
        </div>

        {/* Project Stats */}
        <div className="px-6 py-4 bg-gray-50 grid grid-cols-1 md:grid-cols-4 gap-4">
          <div className="flex items-center">
            <Clock className="h-5 w-5 text-gray-400" />
            <span className="ml-2 text-sm text-gray-600">{project.duration}</span>
          </div>
          <div className="flex items-center">
            <Users className="h-5 w-5 text-gray-400" />
            <span className="ml-2 text-sm text-gray-600">{project.participants} participants</span>
          </div>
          <div className="flex items-center">
            <BarChart className="h-5 w-5 text-gray-400" />
            <span className="ml-2 text-sm text-gray-600">{project.type}</span>
          </div>
          <div className="flex items-center">
            <MessageSquare className="h-5 w-5 text-gray-400" />
            <span className="ml-2 text-sm text-gray-600">Premium support</span>
          </div>
        </div>

        {/* Project Content */}
        <div className="px-6 py-8 grid grid-cols-1 md:grid-cols-3 gap-8">
          {/* Main Content */}
          <div className="md:col-span-2 space-y-8">
            <section>
              <h2 className="text-xl font-semibold text-gray-900 mb-4">Technologies Used</h2>
              <div className="flex flex-wrap gap-2">
                {project.technologies.map((tech) => (
                  <span
                    key={tech}
                    className="px-3 py-1 bg-gray-100 text-gray-700 rounded-full text-sm"
                  >
                    {tech}
                  </span>
                ))}
              </div>
            </section>

            <section>
              <h2 className="text-xl font-semibold text-gray-900 mb-4">Requirements</h2>
              <ul className="list-disc list-inside space-y-2 text-gray-600">
                {project.requirements.map((req, index) => (
                  <li key={index}>{req}</li>
                ))}
              </ul>
            </section>

            <section>
              <h2 className="text-xl font-semibold text-gray-900 mb-4">Learning Outcomes</h2>
              <ul className="list-disc list-inside space-y-2 text-gray-600">
                {project.learningOutcomes.map((outcome, index) => (
                  <li key={index}>{outcome}</li>
                ))}
              </ul>
            </section>
          </div>

          {/* Sidebar */}
          <div className="space-y-6">
            <div className="bg-gray-50 rounded-lg p-6">
              <h3 className="text-lg font-medium text-gray-900 mb-4">Get Started</h3>
              <button className="w-full bg-indigo-600 text-white py-2 px-4 rounded-md hover:bg-indigo-700 transition-colors duration-200 mb-4">
                Start Project
              </button>
              <a
                href="#"
                className="w-full flex items-center justify-center py-2 px-4 border border-gray-300 rounded-md text-gray-700 bg-white hover:bg-gray-50 transition-colors duration-200"
              >
                <Github className="h-5 w-5 mr-2" />
                View on GitHub
              </a>
            </div>

            <div className="bg-gray-50 rounded-lg p-6">
              <h3 className="text-lg font-medium text-gray-900 mb-4">Need Help?</h3>
              <p className="text-gray-600 text-sm mb-4">
                Get premium support from experienced developers
              </p>
              <a
                href="/pricing"
                className="text-indigo-600 hover:text-indigo-500 text-sm font-medium"
              >
                Upgrade to Premium →
              </a>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default ProjectDetails;