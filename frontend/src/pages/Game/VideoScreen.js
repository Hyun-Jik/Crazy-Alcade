// VideoScreen.js

import React from "react";
import "./VideoScreen.css";

const VideoScreen = () => {
  const videoUrls = [
    "https://example.com/video1",
    "https://example.com/video2",
    "https://example.com/video3",
    "https://example.com/video4",
    "https://example.com/video5",
  ];

  return (
    <div className="video-screen">
      <div className="user-area">
        <div className="user-video">
          <iframe
            title="User Video"
            src="https://example.com/user-video"
            frameBorder="0"
            allowFullScreen
          ></iframe>
        </div>
        <div className="icon-container">
          <h1 className="mic-icon">🎤</h1>
          <h1 className="sound-icon">🔊</h1>
        </div>
      </div>
      {videoUrls.map((url, index) => (
        <div key={index} className="video-container">
          <iframe
            title={`Video ${index + 1}`}
            src={url}
            frameBorder="0"
            allowFullScreen
          ></iframe>
        </div>
      ))}
      <div>
        <h1 className="chat-icon">🗨️</h1>
      </div>
    </div>
  );
};

export default VideoScreen;
