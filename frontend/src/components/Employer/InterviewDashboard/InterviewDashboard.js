import React from 'react';
import './InterviewDashboard.scss'
import SheduleItem from "./SheduleItem/SheduleItem";
import ReactPaginate from 'react-paginate';
import UpcomingItem from './UpcomingItem/UpcomingItem';
import OfferItem from './OfferItem/OfferItem';

const mockTalents = [
    {
        id: 1,
        img: 'https://images.pexels.com/photos/220453/pexels-photo-220453.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500',
        name: 'Arthur van Hoff',
        position: 'Data Scientist',
        interviews: 'No interviews yet'
    },
    {
        id: 1,
        img: 'https://images.pexels.com/photos/220453/pexels-photo-220453.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500',
        name: 'Arthur van Hoff',
        position: 'Data Scientist',
        interviews: 'No interviews yet'
    },
    {
        id: 1,
        img: 'https://images.pexels.com/photos/220453/pexels-photo-220453.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500',
        name: 'Arthur van Hoff',
        position: 'Data Scientist',
        interviews: 'No interviews yet'
    },
    {
        id: 1,
        img: 'https://images.pexels.com/photos/220453/pexels-photo-220453.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500',
        name: 'Arthur van Hoff',
        position: 'Data Scientist',
        interviews: 'No interviews yet'
    },
    {
        id: 1,
        img: 'https://images.pexels.com/photos/220453/pexels-photo-220453.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500',
        name: 'Arthur van Hoff',
        position: 'Data Scientist',
        interviews: 'No interviews yet'
    },
    {
        id: 1,
        img: 'https://images.pexels.com/photos/220453/pexels-photo-220453.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500',
        name: 'Arthur van Hoff',
        position: 'Data Scientist',
        interviews: 'No interviews yet'
    },
    {
        id: 1,
        img: 'https://images.pexels.com/photos/220453/pexels-photo-220453.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500',
        name: 'Arthur van Hoff',
        position: 'Data Scientist',
        interviews: 'No interviews yet'
    },
    {
        id: 1,
        img: 'https://images.pexels.com/photos/220453/pexels-photo-220453.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500',
        name: 'Arthur van Hoff',
        position: 'Data Scientist',
        interviews: 'No interviews yet'
    }
];

const mockUpcomingInterviews = [
    {
        id: 1,
        img: 'https://images.pexels.com/photos/220453/pexels-photo-220453.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500',
        name: 'Arthur van Hoff',
        position: 'Data Scientist',
        day: 'Friday, Mar 11',
        time: '12:00-5:00',
        description: 'OnSite interview 1435 Market St., San Francisco, CA 94302, USA'
    },
    {
        id: 1,
        img: 'https://images.pexels.com/photos/220453/pexels-photo-220453.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500',
        name: 'Arthur van Hoff',
        position: 'Data Scientist',
        day: 'Friday, Mar 11',
        time: '12:00-5:00',
        description: 'OnSite interview 1435 Market St., San Francisco, CA 94302, USA'
    },
];

const mockOffers = [
    {
        id: 1,
        img: 'https://images.pexels.com/photos/220453/pexels-photo-220453.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500',
        name: 'Arthur van Hoff',
        position: 'Data Scientist',
        day: 'Friday, Mar 11',
        time: '12:00-5:00',
        description: 'OnSite interview 1435 Market St., San Francisco, CA 94302, USA'
    }
];

function InterviewDashboard(props) {
    const sheduleList = mockTalents.map(talent => <SheduleItem talent={talent} key={talent.id}/>);
    const upcomingsList = mockUpcomingInterviews.map(interview => <UpcomingItem key={interview.id} interview={interview} />);
    const offersList = mockOffers.map(offer => <OfferItem key={offer.id} offer={offer} />);
    return (
        <div className='inreview-dashboard'>
            <div className='inreview-dashboard__left-side'>
                <div className='upcoming-interviews'>
                    <h2 className='inreview-dashboard__subtitle'>
                        my upcoming interviews
                    </h2>
                    {upcomingsList}
                </div>
                <div className='inreview-offers'>
                    <h2 className='inreview-dashboard__subtitle'>
                        final offers extended
                    </h2>
                    {offersList}
                </div>
            </div>
            <div className='inreview-dashboard__right-side'>
                <div className='inreview-dashboard__talent-list'>
                    <h2 className='inreview-dashboard__subtitle'>
                        need scheduling or decision
                    </h2>
                    {sheduleList}
                </div>
                <ReactPaginate
                    pageCount={20}
                    containerClassName='jobs-pagination'
                    pageClassName='jobs-pagination__item'
                    pageLinkClassName='jobs-pagination__link'
                    activeLinkClassName='jobs-pagination__link--active'
                    onPageChange={() => {}}
                    previousLinkClassName='jobs-pagination__link--prev'
                    nextLinkClassName='jobs-pagination__link--next'
                    nextLabel=''
                    previousLabel=''
                />
            </div>
        </div>
    )
}

export default InterviewDashboard;

