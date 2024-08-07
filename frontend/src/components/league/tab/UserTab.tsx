"use client";

import styled from "styled-components";
import Link from "next/link";

import { UserTabProps, TabButtonProps } from "@/types/leagueTypes";

// 아이콘
import { FaCar } from "react-icons/fa";
import { MdFactory } from "react-icons/md";

export default function UserTab({
  activeTabName,
  tabs,
  mentionTabs,
}: UserTabProps) {
  return (
    <>
      <TabList>
        {tabs.map((tab) => (
          <TabButton
            key={tab.id}
            href={`/league/${tab.name}`}
            $isActive={activeTabName === tab.name}
          >
            {activeTabName === tab.name &&
              (tab.type === "BRAND" ? <MdFactory /> : <FaCar />)}
            {tab.name}
          </TabButton>
        ))}
        {mentionTabs.map((tab) => (
          <TabButton
            key={tab.id}
            href={`/league/mention${tab.name}`}
            $isActive={activeTabName === `mention${tab.name}`}
          >
            @{tab.name}
          </TabButton>
        ))}
      </TabList>
    </>
  );
}

const TabList = styled.div`
  display: flex;
  align-items: center;
`;

const TabButton = styled(Link)<TabButtonProps>`
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 10px 5px;
  min-width: 80px;
  max-height: 40px;
  cursor: pointer;
  background: ${(props) => (props.$isActive ? "#FFEDD5" : "none")};
  border: none;
  border-radius: 20px;
  font-size: 14px;
  font-weight: ${(props) => (props.$isActive ? "bold" : "normal")};
  color: ${(props) => (props.$isActive ? "#F97316" : "#333")};
  text-decoration: none;

  &:hover {
    color: #f97316;
  }

  svg {
    margin-right: 5px;
  }
`;
