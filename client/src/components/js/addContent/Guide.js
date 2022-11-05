import React from 'react'
import pencil from '../../../assets/imgs/pencil.png'
import '../../css/addContent/Guide.scss'


export default function Guide({type}) {
    let data = ['Writing a good title', 'Your title should summarize the problem.','You might find that you have a better idea of your title after writing out the rest of the question.'];

    if(type === 'content'){
        data = ['Introduce the problem', 'Explain how you encountered the problem', 'you’re trying to solve, and any difficulties that have prevented you from solving it yourself.']
    }
    else if(type === 'tag'){
        data = ['Adding tags', 'Tags help ensure that your question will get attention from the right people.', 'Tag things in more than one way so people can find them more easily. Add tags for product lines, projects, teams, and the specific technologies or languages used.']
    }
    return(
        <div className='guide'>
            <div className='title'>
                <h2>{data[0]}</h2>
            </div>
            <div className='bottom'>
                <img src={pencil} alt='pencil' />
                <div className='content'>
                    <p className='first'>{data[1]}</p>
                    <p>{data[2]}</p>
                </div>
            </div>
        </div>
    )
}