import React, {Fragment} from 'react';
import Footer from '../../components/Main/Footer/Footer'
import MainMenu from '../../components/Main/MainMenu/MainMenu'
import search from '../../assets/icons/search.svg';

import './Blog.scss'

function Blog(props) {
    return (
        <Fragment>
        <MainMenu/>
        <section className='blog-header'>
            <h1 className='blog-header__title'>stay up to date with the latest
                news оf hiring</h1>
            <div className='blog-header__image-filter'></div>
        </section>
        <section className='blog-container'>
            <div className='blog-content'>
                <ul className='blog-list'>
                    <li className='blog-item'>
                        <div className='blog-item__description'>december, 17 2019 by tom rise in interview process</div>
                        <h2 className='blog-item__header'>9 tips for effective interviewing</h2>
                        <div className='blog-item__container'>
                            <div className='blog-item__icon'></div>
                            <div className='blog-item__text'>
                                As a job candidate, your No. 1 goal is to present and, when possible,
                                highlight for your evaluators the strong points of your training, experience,
                                ability and potential. Here are some tips to help you achieve that goal.
                            </div>
                        </div>
                        <div className='blog-item__link'>continue reading</div>
                    </li>
                    <li className='blog-item'>
                        <div className='blog-item__description'>december, 17 2019 by tom rise in interview process</div>
                        <h2 className='blog-item__header'>9 tips for effective interviewing</h2>
                        <div className='blog-item__container'>
                            <div className='blog-item__icon'></div>
                            <div className='blog-item__text'>
                                As a job candidate, your No. 1 goal is to present and, when possible,
                                highlight for your evaluators the strong points of your training, experience,
                                ability and potential. Here are some tips to help you achieve that goal.
                            </div>
                        </div>
                        <div className='blog-item__link'>continue reading</div>
                    </li>
                    <li className='blog-item'>
                        <div className='blog-item__description'>december, 17 2019 by tom rise in interview process</div>
                        <h2 className='blog-item__header'>9 tips for effective interviewing</h2>
                        <div className='blog-item__container'>
                            <div className='blog-item__icon'></div>
                            <div className='blog-item__text'>
                                As a job candidate, your No. 1 goal is to present and, when possible,
                                highlight for your evaluators the strong points of your training, experience,
                                ability and potential. Here are some tips to help you achieve that goal.
                            </div>
                        </div>
                        <div className='blog-item__link'>continue reading</div>
                    </li>
                    <li className='blog-item'>
                        <div className='blog-item__description'>december, 17 2019 by tom rise in interview process</div>
                        <h2 className='blog-item__header'>9 tips for effective interviewing</h2>
                        <div className='blog-item__container'>
                            <div className='blog-item__icon'></div>
                            <div className='blog-item__text'>
                                As a job candidate, your No. 1 goal is to present and, when possible,
                                highlight for your evaluators the strong points of your training, experience,
                                ability and potential. Here are some tips to help you achieve that goal.
                            </div>
                        </div>
                        <a className='blog-item__link'>continue reading</a>
                    </li>
                </ul>
            </div>
            <div className='blog-sidebar'>
                <form className='search-form'>
                    <input className='search-input' placeholder='Search'/>
                    <span className='search-icon'><img src={search}/></span>
                </form>
                <h2 className='posts-title'>latest posts</h2>
                <div className='posts-menu'>
                    <ul className='posts-list'>
                        <li className="posts-list__item">9 tips for effective interviewing</li>
                        <li className="posts-list__item">Why is important to be great
                            developer</li>
                        <li className="posts-list__item">Salary expectations in 2019</li>
                        <li className="posts-list__item">Hired over 1M in Amazon company </li>
                        <li className="posts-list__item">How to Attract, Source and Retain Top
                            Tech Talent </li>
                    </ul>
                </div>
                <h2 className='posts-title'>categories</h2>
                <div className='categories-list'>
                    <ul className='categories-menu'>
                        <li className="posts-list__item">Interview process</li>
                        <li className="posts-list__item">Analytics</li>
                        <li className="posts-list__item">Mango news</li>
                        <li className="posts-list__item">IT</li>
                        <li className="posts-list__item">Hiring</li>
                    </ul>
                </div>
                <h3 className='form-title'>Subscribe to our Newsletter</h3>
                <form className='subscribe-form'>
                    <input className='mail-input' placeholder='Email' type='email'/>
                    <button className='send-btn'>Subscribe</button>
                </form>
            </div>
        </section>
        <Footer/>
        </Fragment>
    )
}

export default Blog;