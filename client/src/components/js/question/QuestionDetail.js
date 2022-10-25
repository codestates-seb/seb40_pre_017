import React from 'react'
import CommentList from '../comment/CommentList'
import Profile from '../profile/Profile'
import Vote from '../vote/Vote'

export default function QuestionDetail() {
  return (
    <div>QuestionDetail
        <Profile />
        <Vote />
        <CommentList />
    </div>
  )
}
