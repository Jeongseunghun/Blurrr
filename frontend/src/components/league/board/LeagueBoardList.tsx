import styled from "styled-components";
import { useRouter } from "next/navigation";
import LeagueBoardListItem from "./LeagueBoardListItem";

import { fetchLeagueBoardList } from "@/api/league";
import { useEffect, useState } from "react";
import { boardListProp, LeagueBoardItem } from "@/types/leagueTypes";
import { useLeagueStore } from "@/store/leagueStore";

const LeagueBoardList = ({ leagueId, boardList }: boardListProp) => {
  const router = useRouter();
  const { userLeagueList } = useLeagueStore();

  const handleCardClick = (id: string) => {
    const hasAccess = userLeagueList.some((league) => league.id === leagueId);
    if (!hasAccess) {
      alert("허용되지 않은 리그입니다.");
      return;
    }
    router.push(`${leagueId}/${id}`);
  };

  return (
    <BoardList>
      {boardList.map((item) => (
        <div key={item.id} onClick={() => handleCardClick(item.id)}>
          <LeagueBoardListItem
            key={item.id}
            title={item.title}
            writer={item.member.nickname}
            writerCar={item.member.carTitle}
            createdAt={item.createdAt}
            likeCount={item.likeCount}
            commentCount={item.commentCount}
          />
        </div>
      ))}
    </BoardList>
  );
};
const BoardList = styled.div``;

export default LeagueBoardList;
