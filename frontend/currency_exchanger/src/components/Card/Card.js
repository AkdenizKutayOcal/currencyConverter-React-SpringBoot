import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Card from '@material-ui/core/Card';
import CardContent from '@material-ui/core/CardContent';
import Typography from '@material-ui/core/Typography';

const useStyles = makeStyles({
  root: {
    minWidth: 275,
  },
  bullet: {
    display: 'inline-block',
    margin: '0 2px',
    transform: 'scale(0.8)',
  },
  title: {
    fontSize: 16,
  },
  pos: {
    marginBottom: 12,
  },
});

export default function SimpleCard({from,to,amount,value}) {
  const classes = useStyles();
  
  return (
    <Card className={classes.root}>
      <CardContent>
        <Typography className={classes.title} color="textSecondary" gutterBottom>
          {amount + "  " + from + " ="}
        </Typography>
        <Typography variant="h5" component="h2">
          {(value*amount) + "  "+ to}
        </Typography>
      </CardContent>
    </Card>
  );
}