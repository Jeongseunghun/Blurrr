import React from "react";
import styled from "styled-components";

interface option {
  voteCount: number;
  percentage?: number;
}

interface blackboxArticle {
  title: string;
  totalVotes: number;
  optionNumber: number;
  options: option[];
}

function BlackboxListItem({
  title,
  totalVotes,
  optionNumber,
  options,
}: blackboxArticle) {
  const optionPercentage = options.map((option) => ({
    ...option,
    percentage: Math.round((option.voteCount / totalVotes) * 100),
  }));

  if (!optionPercentage) {
    return <div>loading...</div>;
  }

  // 옵션 퍼센티지에 따라 색상을 설정합니다.
  const sortedOptions = [...optionPercentage].sort(
    (a, b) => b.percentage! - a.percentage!
  );
  const colors = ["#FF900D", "#ecb277", "#e0e0e0"];

  // 원래 순서대로 색상을 매핑합니다.
  const coloredOptions = optionPercentage.map((option) => {
    const index = sortedOptions.findIndex(
      (sortedOption) => sortedOption === option
    );
    return {
      ...option,
      color: colors[index],
    };
  });

  return (
    <Container>
      <ArticleInfo>
        <Title>{title}</Title>
        <Participants>{totalVotes}명 참여</Participants>
      </ArticleInfo>
      <BarContainer>
        {coloredOptions.map((option, index) => (
          <Bar key={index} width={option.percentage} color={option.color}>
            {option.percentage}%
          </Bar>
        ))}
      </BarContainer>
    </Container>
  );
}

export default BlackboxListItem;

const Container = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 10px;
  width: 100%;
  border-bottom: 1.6px solid ${({ theme }) => theme.colors.articleDivider};
`;

const ArticleInfo = styled.div`
  display: flex;
  flex-direction: column;
`;

const Title = styled.p`
  font-size: 18px;
  color: black;
  margin-bottom: 8px;
  margin-top: 12px;
`;

const Participants = styled.div`
  font-size: 12px;
  color: ${({ theme }) => theme.colors.subDiscription};
  margin-bottom: 8px;
`;

const BarContainer = styled.div`
  display: flex;
  align-items: center;
  height: 24px;
  width: 50%;
  background-color: #e0e0e0;
  border-radius: 12px;
  overflow: hidden;
`;

const Bar = styled.div<{ width?: number; color?: string }>`
  height: 100%;
  width: ${(props) => props.width}%;
  background-color: ${(props) => props.color};
  display: flex;
  align-items: center;
  justify-content: center;
  color: ${(props) => (props.color === "#e0e0e0" ? "#000" : "#fff")};
  font-size: 12px;
  font-weight: bold;
  text-align: center;
  white-space: nowrap;
  overflow: hidden;
`;
