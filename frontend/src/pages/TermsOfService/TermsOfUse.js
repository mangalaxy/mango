import React from 'react';
import Footer from '../../components/Main/Footer/Footer'
import TopMenuGrey from '../../components/TopMenuGray/TopMenuGrey'
import { Link, animateScroll as scroll } from "react-scroll";
import './TermsOfUse.scss'

function TermsOfUse(props) {
    return (
        <div className='terms-of-use'>
            <TopMenuGrey/>
            <div className='terms-of-use__container'>
                <h1 className='terms-of-use__header'>Terms of service</h1>
                <div className='terms-of-use__content'>
                    <div className='side-menu'>
                        <ul className='side-menu__nav'>
                            <Link
                                activeClass='item-active'
                                to="section1"
                                spy={true}
                                smooth={true}
                                duration={1000}
                            >
                                <li className="side-menu__item">Introduction</li>
                            </Link>
                            <Link
                                activeClass='item-active'
                                to="section2"
                                spy={true}
                                smooth={true}
                                duration={1000}
                            >
                                <li className="side-menu__item">Services</li>
                            </Link>
                            <Link
                                activeClass='item-active'
                                to="section3"
                                spy={true}
                                smooth={true}
                                duration={1000}
                            >
                                <li className="side-menu__item">Registration</li>
                            </Link>
                            <Link
                                activeClass='item-active'
                                to="section4"
                                spy={true}
                                smooth={true}
                                duration={1000}
                            >
                                <li className="side-menu__item">Responsibilities</li>
                            </Link>
                            <Link
                                activeClass='item-active'
                                to="section5"
                                spy={true}
                                smooth={true}
                                duration={1000}
                            >
                                <li className="side-menu__item">Your content</li>
                            </Link>
                            <Link
                                activeClass='item-active'
                                to="section5"
                                spy={true}
                                smooth={true}
                                duration={1000}
                            >
                                <li className="side-menu__item">Licensing to Mango</li>
                            </Link>
                        </ul>
                    </div>
                    <div className='terms-of-use__text'>
                        <h2 className='terms-of-use__sub-header' id='section1'>Introduction</h2>
                        <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p>
                        <h2 className='terms-of-use__sub-header' id='section2'>Services</h2>
                        <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p>
                        <h2 className='terms-of-use__sub-header' id='section3'>Registration</h2>
                        <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p>
                        <h2 className='terms-of-use__sub-header' id='section4'>Responsibilities</h2>
                        <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p>
                        <h2 className='terms-of-use__sub-header' id='section5'>Your content</h2>
                        <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p>
                        <h2 className='terms-of-use__sub-header' id='section6'>Licensing to Mango</h2>
                        <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p>
                    </div>
                </div>
            </div>
            <Footer/>
        </div>
    )
}

export default TermsOfUse;