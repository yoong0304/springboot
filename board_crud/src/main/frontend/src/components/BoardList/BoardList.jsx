import React from "react";
import BoardBox from "../BoardBox/BoardBox";

const BoardList = (props) => {
    console.log('boardList/props: ', props);
    console.log('boardList/props.data: ', props.data);
    return (
        <>
            {Array.isArray(props.data) ?
                props.data.map((i) => (
                    <BoardBox
                        key = {i.id}
                        id = {i.id}
                        title = {i.title}
                        content = {i.content}
                    />
                ))
            : null}
        </>
    );
};
export default BoardList;