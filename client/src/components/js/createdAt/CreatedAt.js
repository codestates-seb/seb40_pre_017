
const createdAt = (date) => {

    const dayjs = require('dayjs')
    const relativeTime = require('dayjs/plugin/relativeTime')
    dayjs.extend(relativeTime)
    let createdAt = dayjs(date).fromNow()

    return createdAt
}

export default createdAt
