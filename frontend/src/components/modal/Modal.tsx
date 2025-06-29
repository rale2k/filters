import { Close } from '@mui/icons-material'
import './style.css'
import {
  Dialog,
  DialogTitle,
  Typography,
  IconButton,
  DialogContent,
} from '@mui/material'

interface ModalProps {
  open: boolean
  handleClose: () => void
  modalTitle: string
  children: React.ReactNode
}

const Modal = (props: ModalProps) => {
  const { open, handleClose, children, modalTitle } = { ...props }

  return (
    <Dialog open={open} onClose={handleClose} maxWidth="md" fullWidth>
      <DialogTitle className="modal-title-bar" sx={{ backgroundColor: 'primary.main' }}>
        <Typography variant="h6" component="div">
          {modalTitle}
        </Typography>
        <IconButton onClick={handleClose} className="modal-close-button">
          <Close />
        </IconButton>
      </DialogTitle>
      <DialogContent className="modal-content">
        {children}
      </DialogContent>
    </Dialog>
  )
}

export default Modal
