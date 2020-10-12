import React, {useRef} from 'react';
import {SideLink} from '../PrivacyPolicy/PrivacyPolicy';

const TermsOfUse = () => {

  const doc = useRef(null);

  return (
      <div className='doc-with-menu'>
        <div className='container'>
          <h1 className='document-page__header'>Terms of service</h1>
          <div className='document-page__content'>
            <div className='side-menu'>
              <ul className='side-menu__nav'>
                <SideLink
                    target={doc}
                    activeClass='item-active'
                    to="section1"
                >
                  <li className="side-menu__item">Introduction</li>
                </SideLink>
                <SideLink
                    target={doc}
                    activeClass='item-active'
                    to="section2"
                >
                  <li className="side-menu__item">Services</li>
                </SideLink>
                <SideLink
                    target={doc}
                    activeClass='item-active'
                    to="section3"
                >
                  <li className="side-menu__item">Registration</li>
                </SideLink>
                <SideLink
                    target={doc}
                    activeClass='item-active'
                    to="section4"
                >
                  <li className="side-menu__item">Responsibilities</li>
                </SideLink>
                <SideLink
                    target={doc}
                    activeClass='item-active'
                    to="section5"
                >
                  <li className="side-menu__item">Your content</li>
                </SideLink>
                <SideLink
                    target={doc}
                    activeClass='item-active'
                    to="section5"
                >
                  <li className="side-menu__item">Licensing to Mango</li>
                </SideLink>
              </ul>
            </div>
            <div ref={doc}  className='doc-with-menu-text'>
              <h2 className='document-page__sub-header'
                  id='section1'>Introduction</h2>
              <p>Lorem Ipsum is simply dummy text of the printing and
                typesetting industry. Lorem Ipsum has been the industry's
                standard dummy text ever since the 1500s, when an unknown
                printer took a galley of type and scrambled it to make a type
                specimen book. It has survived not only five centuries, but also
                the leap into electronic typesetting, remaining essentially
                unchanged. It was popularised in the 1960s with the release of
                Letraset sheets containing Lorem Ipsum passages, and more
                recently with desktop publishing software like Aldus PageMaker
                including versions of Lorem Ipsum.</p>
              <h2 className='document-page__sub-header'
                  id='section2'>Services</h2>
              <p>Lorem Ipsum is simply dummy text of the printing and
                typesetting industry. Lorem Ipsum has been the industry's
                standard dummy text ever since the 1500s, when an unknown
                printer took a galley of type and scrambled it to make a type
                specimen book. It has survived not only five centuries, but also
                the leap into electronic typesetting, remaining essentially
                unchanged. It was popularised in the 1960s with the release of
                Letraset sheets containing Lorem Ipsum passages, and more
                recently with desktop publishing software like Aldus PageMaker
                including versions of Lorem Ipsum.</p>
              <h2 className='document-page__sub-header'
                  id='section3'>Registration</h2>
              <p>Lorem Ipsum is simply dummy text of the printing and
                typesetting industry. Lorem Ipsum has been the industry's
                standard dummy text ever since the 1500s, when an unknown
                printer took a galley of type and scrambled it to make a type
                specimen book. It has survived not only five centuries, but also
                the leap into electronic typesetting, remaining essentially
                unchanged. It was popularised in the 1960s with the release of
                Letraset sheets containing Lorem Ipsum passages, and more
                recently with desktop publishing software like Aldus PageMaker
                including versions of Lorem Ipsum.</p>
              <h2 className='document-page__sub-header'
                  id='section4'>Responsibilities</h2>
              <p>Lorem Ipsum is simply dummy text of the printing and
                typesetting industry. Lorem Ipsum has been the industry's
                standard dummy text ever since the 1500s, when an unknown
                printer took a galley of type and scrambled it to make a type
                specimen book. It has survived not only five centuries, but also
                the leap into electronic typesetting, remaining essentially
                unchanged. It was popularised in the 1960s with the release of
                Letraset sheets containing Lorem Ipsum passages, and more
                recently with desktop publishing software like Aldus PageMaker
                including versions of Lorem Ipsum.</p>
              <h2 className='document-page__sub-header' id='section5'>Your
                content</h2>
              <p>Lorem Ipsum is simply dummy text of the printing and
                typesetting industry. Lorem Ipsum has been the industry's
                standard dummy text ever since the 1500s, when an unknown
                printer took a galley of type and scrambled it to make a type
                specimen book. It has survived not only five centuries, but also
                the leap into electronic typesetting, remaining essentially
                unchanged. It was popularised in the 1960s with the release of
                Letraset sheets containing Lorem Ipsum passages, and more
                recently with desktop publishing software like Aldus PageMaker
                including versions of Lorem Ipsum.</p>
              <h2 className='document-page__sub-header' id='section6'>Licensing
                to Mango</h2>
              <p>Lorem Ipsum is simply dummy text of the printing and
                typesetting industry. Lorem Ipsum has been the industry's
                standard dummy text ever since the 1500s, when an unknown
                printer took a galley of type and scrambled it to make a type
                specimen book. It has survived not only five centuries, but also
                the leap into electronic typesetting, remaining essentially
                unchanged. It was popularised in the 1960s with the release of
                Letraset sheets containing Lorem Ipsum passages, and more
                recently with desktop publishing software like Aldus PageMaker
                including versions of Lorem Ipsum.</p>
            </div>
          </div>
        </div>
      </div>
  );
};

export default TermsOfUse;