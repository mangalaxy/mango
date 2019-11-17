import React from 'react';
import {Link} from 'react-scroll';
import './PrivacyPolicy.scss';

function PrivacyPolicy(props) {
    return (
        <div className='privacy-policy'>
            <div className='privacy-policy-container'>
                <h1 className='privacy-policy-container__header'>Privacy Policy</h1>
                <div className='privacy-policy-content'>
                    <div className='side-menu'>
                        <ul className='side-menu__nav'>
                            <Link
                                activeClass='item-active'
                                to="section1"
                                spy={true}
                                smooth={true}
                                duration={1000}
                            >
                                <li className="side-menu__item">Privacy Statement and Notice</li>
                            </Link>
                            <Link
                                activeClass='item-active'
                                to="section2"
                                spy={true}
                                smooth={true}
                                duration={1000}
                            >
                                <li className="side-menu__item">What information we collect</li>
                            </Link>
                            <Link
                                activeClass='item-active'
                                to="section3"
                                spy={true}
                                smooth={true}
                                duration={1000}
                            >
                                <li className="side-menu__item">Sharing your information</li>
                            </Link>
                            <Link
                                activeClass='item-active'
                                to="section4"
                                spy={true}
                                smooth={true}
                                duration={1000}
                            >
                                <li className="side-menu__item">Email communication</li>
                            </Link>
                            <Link
                                activeClass='item-active'
                                to="section5"
                                spy={true}
                                smooth={true}
                                duration={1000}
                            >
                                <li className="side-menu__item">Public Profile</li>
                            </Link>
                            <Link
                                activeClass='item-active'
                                to="section5"
                                spy={true}
                                smooth={true}
                                duration={1000}
                            >
                            <li className="side-menu__item">Log files</li>
                            </Link>
                        </ul>
                    </div>
                    <div className='privacy-policy-text'>
                        <h2 className='sub-header' id='section1'>Privacy Statement and Notice</h2>
                        <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p>
                        <h2 className='sub-header' id='section2'>What information we collect</h2>
                        <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p>
                        <h2 className='sub-header' id='section3'>Sharing your information</h2>
                        <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p>
                        <h2 className='sub-header' id='section4'>Email communication</h2>
                        <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p>
                        <h2 className='sub-header' id='section5'>Public profile</h2>
                        <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p>
                        <h2 className='sub-header' id='section6'>Log files</h2>
                        <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default PrivacyPolicy;