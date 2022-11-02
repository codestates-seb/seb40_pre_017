import React from 'react'
import '@toast-ui/editor/dist/toastui-editor.css';

import { Editor } from '@toast-ui/react-editor';

export default function MarkDown() {
    
  return (
    <Editor
    previewStyle="tab"
    min-height="250px"
    initialEditType="markdown"
    useCommandShortcut={true}
    toolbarItems={
        [['heading', 'bold', 'italic', 'code'],
        ['link', 'quote','codeblock','image','table'],
        ['ul', 'ol'],
        [],
        ['task','hr']]
    }
    />
  )
}
