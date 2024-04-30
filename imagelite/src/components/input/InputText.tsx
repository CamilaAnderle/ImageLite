import React from "react";

interface InputTextProps{
    style?: string;
    onChange?: (event: React.ChangeEvent<HTMLInputElement>) => void
    placeholder?: string;
    id?: string;
    value?: string;
    type?: string;
}

export const InputText: React.FC<InputTextProps> = ({
    style, type = "text",  ...rest
}) => {
    return (
        <input type={type}
                {...rest}
                className={`${style} border px-5 py-2 rounded-lg text-grey-900`}/>
    )
}