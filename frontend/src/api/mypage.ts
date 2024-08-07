import { MyHeartItem, MyPostItem } from '@/types/myPageTypes';
import api from '../api/index'
import axios from 'axios';
  
export const checkPassword = async (password: string, accessToken: string): Promise<boolean> => {
try {
    const response = await api.post('/v1/members/password', { password }, {
    headers: {
        'Authorization': `Bearer ${accessToken}`
    }
    });
    return response.data;
} catch (error) {
    console.error('Password check failed:', error);
    throw new Error('Failed to check password');
}
};

export const getMyHeartLeagueList = async (accessToken: string, pageNumber = 0, criteria = 'TIME'): Promise<MyHeartItem[]> => {
    try {
        const response = await api.get(`/v1/members/likes/boards`, {
            params: { pageNumber, criteria },
            headers: {
                'Authorization': `Bearer ${accessToken}`
            }
        });
        return response.data.data.boards;
    } catch (error) {
        console.error('API 호출 오류:', error);
        throw new Error('Failed to fetch league boards');
    }
};

export const getMyHeartChannelList = async (accessToken: string, pageNumber = 0, criteria = 'TIME'): Promise<MyHeartItem[]> => {
    try {
        const response = await api.get(`/v1/members/likes/boards`, {
            params: { pageNumber, criteria },
            headers: {
                'Authorization': `Bearer ${accessToken}`
            }
        });
        return response.data.data.boards;
    } catch (error) {
        console.error('API 호출 오류:', error);
        throw new Error('Failed to fetch channel boards');
    }
};

export const getMyPostLeagueList = async (accessToken: string, pageNumber = 0, criteria = 'TIME'): Promise<MyPostItem[]> => {
    try {
        const response = await api.get(`/v1/members/posts/leagues`, {
            params: { pageNumber, criteria },
            headers: {
                'Authorization': `Bearer ${accessToken}`
            }
        });
        return response.data.data.boards;
    } catch (error) {
        console.error('API 호출 오류:', error);
        throw new Error('Failed to fetch league boards');
    }
};

export const getMyPostChannelList = async (accessToken: string, pageNumber = 0, criteria = 'TIME'): Promise<MyPostItem[]> => {
    try {
        const response = await api.get(`/v1/members/posts/channels`, {
            params: { pageNumber, criteria },
            headers: {
                'Authorization': `Bearer ${accessToken}`
            }
        });
        return response.data.data.boards;
    } catch (error) {
        console.error('API 호출 오류:', error);
        throw new Error('Failed to fetch channel boards');
    }
};