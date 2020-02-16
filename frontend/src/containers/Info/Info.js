import React from 'react';
import './Info.scss'
import {like, twitter, facebook} from "../../assets/icons";
import SvgIcon from '../../components/SvgIcon/SvgIcon';

function Info(props) {
    const id = props.match.params.id;
    return (
        <div className='info'>
            <div className='info__container'>
                <div className='info__bread-crumbs'>
                    Blog > How to Succeed at a Career Fair
                </div>
                <h1 className='info__title'>9 tips for effective interviewing</h1>
                <div className="info__description">december, 17 2019 by tom rise in <a className='info-description-link'>interview process</a></div>
                <div className='info__main-photo'>
                    <img className='info-main-img' src='https://info.info/sites/afilias-tld/files/styles/homepage_image/public/Phase2-Slide-%28final%29_3.jpg?itok=7H6kVDrv'/>
                </div>
                <div className='info__content'>
                    <div className='info__left-sidebar'>
                        <div className='info__sidebar-item'><SvgIcon className='info__sidebar-item' type={like()}/></div>
                        <div className='info__sidebar-item'><SvgIcon className='info__sidebar-item' type={twitter()}/></div>
                        <div className='info__sidebar-item'><SvgIcon className='info__sidebar-item' type={facebook()}/></div>
                    </div>
                    <div className='info__text'>
                        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Enim ut sem viverra aliquet eget sit. At volutpat diam ut venenatis tellus in. Morbi tristique senectus et netus. Sagittis purus sit amet volutpat consequat mauris nunc.
                        </p>
                        <p>Quam lacus suspendisse faucibus interdum posuere lorem ipsum dolor sit. Donec ultrices tincidunt arcu non sodales. Nulla facilisi nullam vehicula ipsum. Neque convallis a cras semper auctor neque vitae tempus. Faucibus interdum posuere lorem ipsum dolor sit amet consectetur adipiscing.
                        </p>
                        <p>Ac felis donec et odio pellentesque diam volutpat commodo sed. Faucibus turpis in eu mi bibendum neque egestas congue. Quis risus sed vulputate odio ut. Suspendisse potenti nullam ac tortor. Auctor eu augue ut lectus arcu bibendum at varius vel. Rutrum quisque non tellus orci ac auctor augue mauris augue. Sit amet nulla facilisi morbi tempus iaculis urna id. In egestas erat imperdiet sed euismod nisi porta lorem mollis.
                        </p>
                        <p>Quisque sagittis purus sit amet volutpat consequat mauris nunc congue. Mi tempus imperdiet nulla malesuada pellentesque. In tellus integer feugiat scelerisque varius morbi enim nunc. Est ullamcorper eget nulla facilisi etiam. Viverra vitae congue eu consequat ac. Vitae auctor eu augue ut lectus arcu.
                        </p>
                        <p>Lacus vestibulum sed arcu non odio euismod lacinia. In eu mi bibendum neque egestas congue. Elit ut aliquam purus sit amet luctus venenatis. Ornare massa eget egestas purus.
                        </p>
                        <div className='info__bottom-sidebar'>
                            <div className='info__bottom-item'><SvgIcon className='info__sidebar-item' type={like()}/></div>
                            <div className='info__bottom-item'><SvgIcon className='info__sidebar-item' type={twitter()}/></div>
                            <div className='info__bottom-item'><SvgIcon className='info__sidebar-item' type={facebook()}/></div>
                        </div>
                    </div>
                </div>
                <div className='info__similar-list'>
                    <div className="info__list-description">Similar articles:</div>
                    <div className='info-list'>
                        <div className='info-list__item'>
                            <div className='info-photo'>
                                <img className='info-photo__img' src='https://nashagazeta.ch/sites/default/files/graphic-design.jpg'/>
                            </div>
                            <div className='info__tag'>Design</div>
                            <div className='info__subtitle'>Principles For Designing Better Products</div>
                        </div>
                        <div className='info-list__item'>
                            <div className='info-photo'>
                                <img className='info-photo__img' src='https://nashagazeta.ch/sites/default/files/graphic-design.jpg'/>
                            </div>
                            <div className='info__tag'>Design</div>
                            <div className='info__subtitle'>Principles For Designing Better Products</div>
                        </div>
                        <div className='info-list__item'>
                            <div className='info-photo'>
                                <img className='info-photo__img' src='https://nashagazeta.ch/sites/default/files/graphic-design.jpg'/>
                            </div>
                            <div className='info__tag'>Design</div>
                            <div className='info__subtitle'>Principles For Designing Better Products</div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default Info;