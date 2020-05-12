import React, {Fragment, useState} from 'react';
import './MobileMenu.scss';
import {routes} from "../../../constants/routes";
import Down from "../../Down/Down";
import SvgIcon from '../../../components/SvgIcon/SvgIcon';
import {close} from "../../../assets/icons";
import {connect} from "react-redux";
import {closeMobileMenu} from "../../../actions/mobileMenu";
import {Link, NavLink} from 'react-router-dom';

function ModbileMenu(props) {
    const {openLoginForm, openSignUpTalent, openSignUpEmployer, closeMobileMenu} = props;
    const [openSubmenu, toggleMenuOpen] = useState(false)

    const logIn = () => {
        openLoginForm();
        closeMobileMenu();
    };

    const signUpTalent = () => {
        openSignUpTalent();
        closeMobileMenu()
    };

    const signUpEmployer = () => {
        openSignUpEmployer();
        closeMobileMenu();
    }

    return (
        <Fragment>
            <div className='mobile-container'>
            </div>
            <div className='mobile-content'>
                <div className='mobile-header'>
                    <div className='logo'>
                        <Link to={routes.HOME} className='logo'>
                            Mangostart
                        </Link>
                    </div>
                    <div className='mobile-close' onClick={closeMobileMenu}><SvgIcon type={close()} /></div>
                </div>
                <ul className='mobile-menu'>
                    <li className='mobile-menu__item'>
                        <NavLink to={routes.FOR_TALENTS}
                                 activeClassName={''}
                                 className='mobile-menu__link'>
                            Talents
                        </NavLink>
                    </li>
                    <li className='mobile-menu__item'>
                        <NavLink to={routes.FOR_EMPLOYERS}
                                 activeClassName={''}
                                 className='mobile-menu__link'>
                            Employers
                        </NavLink>
                    </li>
                    <li className='mobile-menu__item'>
                        <NavLink to={routes.FIND_JOB}
                                 activeClassName={''}
                                 className='mobile-menu__link'>
                            Jobs
                        </NavLink>
                    </li>
                    <li className='mobile-menu__item'>
                        <NavLink to={routes.BLOG}
                                 activeClassName={''}
                                 className='mobile-menu__link'>
                            Blog
                        </NavLink>
                    </li>
                    <li className='mobile-menu__item' onClick={logIn}>
                        Log in
                    </li>
                    <li className='mobile-menu__item' onClick={() => toggleMenuOpen(!openSubmenu)}>
                        Sign up
                        <Down background='#4f4f4f' className='mobile-menu__dropdown-chevron'/>
                        <ul className={`mobile-menu__submenu ${!openSubmenu && 'mobile-menu__submenu--closed'}`}>
                            <li className='mobile-menu__item mobile-menu__item--submenu' onClick={signUpTalent}>Talents</li>
                            <li className='mobile-menu__item mobile-menu__item--submenu' onClick={signUpEmployer}>Employers</li>
                        </ul>
                    </li>
                </ul>
            </div>
        </Fragment>
    )
}

const mapStateToProps = () => {
    return {

    }
};

const mapDispatchToProps = dispatch => ({
    closeMobileMenu: () => dispatch(closeMobileMenu())
});

export default connect(mapStateToProps, mapDispatchToProps)(ModbileMenu);

