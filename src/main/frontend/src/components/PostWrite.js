import React, { useState } from 'react';
import axios from 'axios';

const PostWrite = () => {
  const [title, setTitle] = useState('');
  const [content, setContent] = useState('');
  const [category, setCategory] = useState('DESK_SETUP');
  // eslint-disable-next-line
  const [memberId, setMemberId] = useState(1); // 이 값을 실제 로그인된 사용자 ID로 변경
  const [images, setImages] = useState([]);

  const handleImageChange = (e) => {
    setImages([...e.target.files]);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    const formData = new FormData();
    const post = {
      title,
      content,
      category,
      memberId
    };
    
    formData.append('post', new Blob([JSON.stringify(post)], { type: "application/json" }));
    images.forEach((image) => {
      formData.append('images', image);
    });

    try {
      const response = await axios.post('/api/posts', formData, {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      });
      console.log('Post successful:', response.data);
      // 성공 시 추가적인 작업 수행
    } catch (error) {
      console.error('Post failed:', error);
      // 실패 시 추가적인 작업 수행
    }
  };

  return (
    <div>
      <h1>Post Write</h1>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Title:</label>
          <input
            type="text"
            value={title}
            onChange={(e) => setTitle(e.target.value)}
          />
        </div>
        <div>
          <label>Content:</label>
          <textarea
            value={content}
            onChange={(e) => setContent(e.target.value)}
          />
        </div>
        <div>
          <label>Category:</label>
          <select
            value={category}
            onChange={(e) => setCategory(e.target.value)}
          >
            <option value="DESK_SETUP">DESK_SETUP</option>
            <option value="EQUIPMENT_INFO">EQUIPMENT_INFO</option>
          </select>
        </div>
        <div>
          <label>Images:</label>
          <input
            type="file"
            multiple
            onChange={handleImageChange}
          />
        </div>
        <button type="submit">Submit</button>
      </form>
    </div>
  );
};

export default PostWrite;
