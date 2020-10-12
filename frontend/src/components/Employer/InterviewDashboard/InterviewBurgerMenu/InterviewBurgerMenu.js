import React, {useEffect, useState} from 'react';

function InterviewBurgerMenu(props) {
    const [menuIsOpen, menuToggleOpen] = useState(false);
    const openMenu = () => {
        menuToggleOpen(!menuIsOpen);
    };

    useEffect(() => {
        document.body.addEventListener('click', () => {
            menuToggleOpen(false)
        })
    }, []);

    return (
        <div className={`interview-burger-menu ${menuIsOpen && 'interview-burger-menu--active'}`} onClick={openMenu}>
            <div className='interview-burger-menu__list'>
                <div className='interview-burger-menu__item'></div>
                <div className='interview-burger-menu__item'></div>
                <div className='interview-burger-menu__item'></div>
            </div>
            {
                menuIsOpen &&
                <ul className='interviews-menu'>
                    <li className='interviews-menu__item'>Schedule Interview</li>
                    <li className='interviews-menu__item'>Move to Final Offer</li>
                    <li className='interviews-menu__item'>Reject</li>
                </ul>
            }

        </div>
    )
}

export default InterviewBurgerMenu;
