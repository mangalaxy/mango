import React from 'react';
import './BurgerMenu.scss'
import {connect} from "react-redux";
import {openMobileMenu} from "../../../../actions/mobileMenu";

function BurgerMenu(props) {
    const {openMobileMenu} = props;

    return (
        <div className='burger-container'>
           <div className='menu-button' onClick={openMobileMenu}>
               <div className='menu-button__line'></div>
               <div className='menu-button__line'></div>
               <div className='menu-button__line'></div>
           </div>
        </div>
    )
}

const mapStateToProps = () => {
    return {
    }
};

const mapDispatchToProps = dispatch => ({
    openMobileMenu: () => dispatch(openMobileMenu())
});

export default connect(mapStateToProps, mapDispatchToProps)(BurgerMenu);