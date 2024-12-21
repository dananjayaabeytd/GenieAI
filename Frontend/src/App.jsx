import { useState } from 'react';
import './App.css';
import Chat from './components/Chat';
import ImageGen from './components/ImageGen';
import RecipeGen from './components/RecipeGen';

function App() {
  const [activeTab, setActiveTab] = useState('image-generator');

  const handleTabChange = tab => {
    setActiveTab(tab);
  };

  return (
    <div className='App'>
      <button
        className={activeTab === 'image-generator' ? 'active' : ''}
        onClick={() => handleTabChange('image-generator')}
      >
        Image Generator
      </button>
      <button
        className={activeTab === 'chat' ? 'active' : ''}
        onClick={() => handleTabChange('chat')}
      >
        Ask AI
      </button>
      <button
        className={activeTab === 'recipe-generator' ? 'active' : ''}
        onClick={() => handleTabChange('recipe-generator')}
      >
        Recipe Generator
      </button>

      <div>
        {activeTab === 'image-generator' && <ImageGen />}
        {activeTab === 'chat' && <Chat />}
        {activeTab === 'recipe-generator' && <RecipeGen />}
      </div>
    </div>
  );
}

export default App;
