import React, {useRef} from 'react';

const PrivacyPolicy = () => {

  const doc = useRef(null);

  return (
      <div className='doc-with-menu'>
        <div className='container'>
          <h1 className='document-page__header'>Privacy Policy</h1>
          <div className='document-page__content'>
            <div className='side-menu'>
              <ul className='side-menu__nav'>
                <SideLink
                    target={doc}
                    activeClass='item-active'
                    to="section1"
                >
                  <li className="side-menu__item">Privacy Statement and Notice
                  </li>
                </SideLink>
                <SideLink
                    target={doc}
                    activeClass='item-active'
                    to="section2"
                >
                  <li className="side-menu__item">What information we collect
                  </li>
                </SideLink>
                <SideLink
                    target={doc}
                    activeClass='item-active'
                    to="section3"
                >
                  <li className="side-menu__item">Sharing your information</li>
                </SideLink>
                <SideLink
                    target={doc}
                    activeClass='item-active'
                    to="section4"
                >
                  <li className="side-menu__item">Email communication</li>
                </SideLink>
                <SideLink
                    target={doc}
                    activeClass='item-active'
                    to="section5"
                >
                  <li className="side-menu__item">Public Profile</li>
                </SideLink>
                <SideLink
                    target={doc}
                    activeClass='item-active'
                    to="section6"
                >
                  <li className="side-menu__item">Log files</li>
                </SideLink>
              </ul>
            </div>
            <div ref={doc} className='doc-with-menu-text'>
              <h2 className='document-page__sub-header' id='section1'>Privacy
                Statement and Notice</h2>
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
              <h2 className='document-page__sub-header' id='section2'>What
                information we collect</h2>
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
              <h2 className='document-page__sub-header' id='section3'>Sharing
                your information</h2>
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
              <h2 className='document-page__sub-header' id='section4'>Email
                communication</h2>
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
              <h2 className='document-page__sub-header' id='section5'>Public
                profile</h2>
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
              <h2 className='document-page__sub-header' id='section6'>Log
                files</h2>
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

export default PrivacyPolicy;

export const SideLink = ({to, children, target}) => {
  const goToTarget = () => {
    if (document.getElementById(to) && target && target.current) {
      let offset = document.getElementById(to).offsetTop;
      target.current.scrollTo({top:offset-target.current.offsetTop, behavior: "smooth"})
    }
  };

  return (
      <span onClick={goToTarget}>{children}</span>
  );
};