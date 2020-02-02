import React from 'react';

export function clock(fill, width, height) {
    return (
        <svg width={width || '20'} height={height || '20'} viewBox="0 0 30 30" fill="none" xmlns="http://www.w3.org/2000/svg">
            <path d="M15 0C6.729 0 0 6.729 0 15C0 23.271 6.729 30 15 30C23.271 30 30 23.271 30 15C30 6.729 23.271 0 15 0ZM15 29C7.2805 29 1 22.7195 1 15C1 7.2805 7.2805 1 15 1C22.7195 1 29 7.2805 29 15C29 22.7195 22.7195 29 15 29Z" fill={fill || "black"} fill-opacity="0.75"/>
            <path d="M15 3C14.724 3 14.5 3.2235 14.5 3.5V15H7C6.724 15 6.5 15.2235 6.5 15.5C6.5 15.7765 6.724 16 7 16H15C15.276 16 15.5 15.7765 15.5 15.5V3.5C15.5 3.2235 15.276 3 15 3Z" fill={fill || "black"} fill-opacity="0.75"/>
        </svg>
    )
}