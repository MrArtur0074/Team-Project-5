import React from 'react';
import { Settings, Award, BookOpen, Star, Github } from 'lucide-react';

const Profile = () => {
  // Mock user data
  const user = {
    name: "Alex Johnson",
    role: "Frontend Developer",
    email: "alex@example.com",
    joinDate: "January 2024",
    subscription: "Premium",
    completedProjects: 5,
    currentProjects: 2,
    skills: ["React", "TypeScript", "Tailwind CSS", "Node.js"],
    achievements: [
      {
        id: 1,
        title: "First Project",
        description: "Completed your first project",
        icon: BookOpen
      },
      {
        id: 2,
        title: "Rising Star",
        description: "Received 5 excellent reviews",
        icon: Star
      }
    ],
    projects: [
      {
        id: 1,
        title: "Portfolio Website",
        status: "completed",
        grade: "A",
        date: "Feb 2024"
      },
      {
        id: 2,
        title: "E-commerce API",
        status: "in-progress",
        date: "Mar 2024"
      }
    ]
  };

  return (
    <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-8">
      {/* Profile Header */}
      <div className="bg-white shadow-sm rounded-lg overflow-hidden mb-8">
        <div className="px-6 py-8">
          <div className="flex items-center justify-between">
            <div className="flex items-center">
              <div className="h-16 w-16 rounded-full bg-indigo-100 flex items-center justify-center">
                <span className="text-2xl font-bold text-indigo-600">
                  {user.name.charAt(0)}
                </span>
              </div>
              <div className="ml-4">
                <h1 className="text-2xl font-bold text-gray-900">{user.name}</h1>
                <p className="text-gray-500">{user.role}</p>
              </div>
            </div>
            <button className="flex items-center px-4 py-2 border border-gray-300 rounded-md text-gray-700 bg-white hover:bg-gray-50">
              <Settings className="h-5 w-5 mr-2" />
              Edit Profile
            </button>
          </div>
        </div>

        <div className="border-t border-gray-200 px-6 py-4 bg-gray-50">
          <div className="grid grid-cols-1 md:grid-cols-4 gap-4">
            <div className="flex items-center">
              <Award className="h-5 w-5 text-gray-400" />
              <span className="ml-2 text-sm text-gray-600">{user.subscription} Member</span>
            </div>
            <div className="flex items-center">
              <BookOpen className="h-5 w-5 text-gray-400" />
              <span className="ml-2 text-sm text-gray-600">{user.completedProjects} Projects Completed</span>
            </div>
            <div className="flex items-center">
              <Star className="h-5 w-5 text-gray-400" />
              <span className="ml-2 text-sm text-gray-600">{user.currentProjects} Active Projects</span>
            </div>
            <div className="flex items-center">
              <Github className="h-5 w-5 text-gray-400" />
              <a href="#" className="ml-2 text-sm text-indigo-600 hover:text-indigo-500">View GitHub</a>
            </div>
          </div>
        </div>
      </div>

      <div className="grid grid-cols-1 md:grid-cols-3 gap-8">
        {/* Skills & Achievements */}
        <div className="space-y-8">
          <div className="bg-white shadow-sm rounded-lg p-6">
            <h2 className="text-lg font-medium text-gray-900 mb-4">Skills</h2>
            <div className="flex flex-wrap gap-2">
              {user.skills.map((skill) => (
                <span
                  key={skill}
                  className="px-3 py-1 bg-gray-100 text-gray-700 rounded-full text-sm"
                >
                  {skill}
                </span>
              ))}
            </div>
          </div>

          <div className="bg-white shadow-sm rounded-lg p-6">
            <h2 className="text-lg font-medium text-gray-900 mb-4">Achievements</h2>
            <div className="space-y-4">
              {user.achievements.map((achievement) => (
                <div key={achievement.id} className="flex items-start">
                  <div className="flex-shrink-0">
                    <achievement.icon className="h-6 w-6 text-indigo-600" />
                  </div>
                  <div className="ml-3">
                    <h3 className="text-sm font-medium text-gray-900">{achievement.title}</h3>
                    <p className="text-sm text-gray-500">{achievement.description}</p>
                  </div>
                </div>
              ))}
            </div>
          </div>
        </div>

        {/* Projects */}
        <div className="md:col-span-2">
          <div className="bg-white shadow-sm rounded-lg p-6">
            <h2 className="text-lg font-medium text-gray-900 mb-4">Projects</h2>
            <div className="space-y-6">
              {user.projects.map((project) => (
                <div
                  key={project.id}
                  className="flex items-center justify-between p-4 border border-gray-200 rounded-lg"
                >
                  <div>
                    <h3 className="text-sm font-medium text-gray-900">{project.title}</h3>
                    <p className="text-sm text-gray-500">{project.date}</p>
                  </div>
                  <div className="flex items-center">
                    {project.grade && (
                      <span className="mr-4 px-2 py-1 bg-green-100 text-green-800 rounded-md text-sm font-medium">
                        Grade: {project.grade}
                      </span>
                    )}
                    <span
                      className={`px-2 py-1 rounded-md text-sm font-medium ${
                        project.status === 'completed'
                          ? 'bg-green-100 text-green-800'
                          : 'bg-yellow-100 text-yellow-800'
                      }`}
                    >
                      {project.status === 'completed' ? 'Completed' : 'In Progress'}
                    </span>
                  </div>
                </div>
              ))}
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Profile;