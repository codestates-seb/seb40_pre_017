
const createdAt = (date) => {

    const dayjs = require('dayjs')
    const relativeTime = require('dayjs/plugin/relativeTime')
    const utc = require('dayjs/plugin/utc')
    dayjs.extend(relativeTime)
    dayjs.extend(utc)
    let createdAt = dayjs(date).utc(true).fromNow()

    return createdAt
}

export default createdAt
