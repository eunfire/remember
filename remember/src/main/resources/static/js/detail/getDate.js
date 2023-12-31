function getDate(dateTime) {
  let dateString = ''
  const date = new Date(dateTime)
  const now = new Date()
  const diff = now.getTime() - date.getTime()

  const diffDay = Math.floor(diff / (1000 * 60 * 60 * 24))
  const diffHour = Math.floor(diff / (1000 * 60 * 60))
  const diffMin = Math.floor(diff / (1000 * 60))
  const diffSec = Math.floor(diff / 1000)

  if (diffSec < 60) dateString = `방금 전`
  else if (diffMin < 60) dateString = `${diffMin}분 전`
  else if (diffHour < 24) dateString = `${diffHour}시간 전`
  else if (diffDay < 8) dateString = `${diffDay}일 전`
  else dateString = dateTime.split('T')[0]

  return dateString
}