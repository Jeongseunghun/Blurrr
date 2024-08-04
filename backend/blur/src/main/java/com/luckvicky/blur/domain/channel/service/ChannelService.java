package com.luckvicky.blur.domain.channel.service;

import com.luckvicky.blur.domain.channel.model.dto.ChannelDto;
import com.luckvicky.blur.domain.channel.model.dto.request.ChannelCreateRequest;
import com.luckvicky.blur.global.jwt.model.ContextMember;
import java.util.List;
import java.util.UUID;

public interface ChannelService {
    ChannelDto createChannel(ChannelCreateRequest request, UUID memberId);
    List<ChannelDto> getAllChannels(ContextMember nullableMember);
    List<ChannelDto> getFollowedChannels(UUID memberId);
    List<ChannelDto> getCreatedChannels(UUID memberId);
    List<ChannelDto> searchChannelsByKeyword(String keyword, ContextMember nullableMember);
    ChannelDto getChannelById(UUID channelId, ContextMember nullableMember);
    boolean createFollow(UUID memberId, UUID channelId);
    boolean deleteFollow(UUID memberId, UUID channelId);
}
