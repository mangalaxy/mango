import React, {Fragment} from 'react';
import MainMenu from "../Main/MainMenu/MainMenu";
import InterviewRequest from './InterviewRequest/InterviewRequest';
import UpcomingInterview from './UpcomingInterview/UpcomingInterview';
import './ProfileInterview.scss'

const mockInterviewRequests = [
    {
        id: 1,
        companyTitle: 'dragon innovation',
        employers: '50-100',
        companyCategory: 'Hardware',
        position: 'BACKEND Engineer',
        salary: '$150,000.00',
        degree: 'Lead Tech Engineer',
        workType: 'Remote',
        companyLogo: 'https://dynamic.brandcrowd.com/asset/logo/227e29d0-3525-410d-9574-8c021cb92f80/logo?v=4',
        text: 'Hi Arthur van Hoff! I will glad to see you in our company at the next week.'
    }
];

const mockInterviews = [
    {
        id: 1,
        company: 'Dragon Innovation',
        position: 'Lead Tech Engineer',
        salary: '$150,000.00',
        employment: 'Remote',
        location: 'Toronto, Canada (GMT-8:00)',
        interviewer: 'Mike Will',
        timeZone: 'GMT+2:00',
        date: 'Tue, 5 March 2019 12:00',
        duration: '1 hour',
        format: 'Phone',
        text: 'Hi Arthur van Hoff! I will glad to see you in our company at the next week.'
    }
]

function ProfileInterview(props) {
    const interviewRequests = mockInterviewRequests.map(request => <InterviewRequest key={request.id} request={request} />);
    const interviews = mockInterviews.map(iterview => <UpcomingInterview key={iterview.id} interview={iterview} />);
    const id = props.match.params.id;

    return (
        <Fragment>
            <div className='interviews-header'>
                <MainMenu grey dark />
            </div>
            <div className='interviews'>
                <div className='interviews-container'>
                    <div className='interviews-requests'>
                        <h2 className='interviews__title'>interview requests</h2>
                        {interviewRequests}
                    </div>
                    <div className='interviews-upcoming'>
                        <h2 className='interviews__title'>my upcoming interviews</h2>
                        {interviews}
                    </div>
                </div>
            </div>
        </Fragment>
    )
}

export default ProfileInterview;